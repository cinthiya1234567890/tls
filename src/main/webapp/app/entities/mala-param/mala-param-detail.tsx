import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './mala-param.reducer';
import { IMalaParam } from 'app/shared/model/mala-param.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMalaParamDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MalaParamDetail = (props: IMalaParamDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { malaParamEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          MalaParam [<b>{malaParamEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="peckOrder">Peck Order</span>
          </dt>
          <dd>{malaParamEntity.peckOrder}</dd>
          <dt>Mala</dt>
          <dd>{malaParamEntity.malaMalaName ? malaParamEntity.malaMalaName : ''}</dd>
          <dt>Field</dt>
          <dd>{malaParamEntity.fieldFieldName ? malaParamEntity.fieldFieldName : ''}</dd>
        </dl>
        <Button tag={Link} to="/mala-param" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/mala-param/${malaParamEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ malaParam }: IRootState) => ({
  malaParamEntity: malaParam.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MalaParamDetail);
