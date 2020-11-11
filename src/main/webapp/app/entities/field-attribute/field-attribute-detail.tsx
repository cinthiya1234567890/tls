import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './field-attribute.reducer';
import { IFieldAttribute } from 'app/shared/model/field-attribute.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFieldAttributeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FieldAttributeDetail = (props: IFieldAttributeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { fieldAttributeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          FieldAttribute [<b>{fieldAttributeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="attributeName">Attribute Name</span>
          </dt>
          <dd>{fieldAttributeEntity.attributeName}</dd>
          <dt>
            <span id="attributeValue">Attribute Value</span>
          </dt>
          <dd>{fieldAttributeEntity.attributeValue}</dd>
          <dt>Field</dt>
          <dd>{fieldAttributeEntity.fieldFieldName ? fieldAttributeEntity.fieldFieldName : ''}</dd>
        </dl>
        <Button tag={Link} to="/field-attribute" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/field-attribute/${fieldAttributeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ fieldAttribute }: IRootState) => ({
  fieldAttributeEntity: fieldAttribute.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FieldAttributeDetail);
