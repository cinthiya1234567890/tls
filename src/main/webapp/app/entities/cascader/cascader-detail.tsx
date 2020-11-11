import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './cascader.reducer';
import { ICascader } from 'app/shared/model/cascader.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICascaderDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CascaderDetail = (props: ICascaderDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cascaderEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Cascader [<b>{cascaderEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="cascaderName">Cascader Name</span>
          </dt>
          <dd>{cascaderEntity.cascaderName}</dd>
          <dt>
            <span id="canEnterCustomValue">Can Enter Custom Value</span>
          </dt>
          <dd>{cascaderEntity.canEnterCustomValue ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/cascader" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/cascader/${cascaderEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cascader }: IRootState) => ({
  cascaderEntity: cascader.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CascaderDetail);
